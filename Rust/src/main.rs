#[macro_use]
extern crate json;
extern crate hyper;
extern crate futures;
extern crate rand;

mod models;

use hyper::{Server, Request, Response, Body, Method, StatusCode};
use hyper::rt::{Future};
use futures::future;
use hyper::service::service_fn;
use models::*;

type BoxFut = Box<Future<Item = Response<Body>, Error = hyper::Error> + Send>;

fn main() {
    let addr = ([127, 0, 0, 1], 3000).into();

    let server = Server::bind(&addr)
        .serve(|| service_fn(route_request))
        .map_err(|e| eprintln!("Server error: {}", e));
    
    println!("Server at addr {}", addr);

    hyper::rt::run(server);
}

fn route_request(req: Request<Body>) -> BoxFut {
    match(req.method(), req.uri().path()){
        (&Method::GET, "/posts") => {
            let location = Point::random();

            let post = Post {
                content: String::from("Hello"),
                location
            };

            let post2 = Post{
                content: String::from("Sebas se la come"),
                location: Point::random()
            };

            let blog_response = PostResponse {
                posts:vec![post, post2],
                continuation_token:0
            };

            let js_obj = json::stringify(blog_response);

            let response = Response::builder()
                .status(StatusCode::OK)
                .header("Content-Type", "application/json")
                .body(Body::from(js_obj))
                .unwrap();

           Box::new(future::ok(response))
        }
        _ => {
            let response = Response::builder()
                .status(StatusCode::OK)
                .body(Body::empty())
                .unwrap();

            Box::new(future::ok(response))
        }
    }
}