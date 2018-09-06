#[macro_use]
extern crate json;
extern crate hyper;
extern crate futures;

mod models;

use hyper::{Server, Request, Response, Body, Method};
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
    let mut response = Response::new(Body::empty());

    match(req.method(), req.uri().path()){
        (&Method::POST, "/posts") => {

            *response.body_mut() = Body::from(String::from("Hello"));
        },
        _ => {
            let location = Point {
                lat: 123.23f32,
                lon: 323.12f32
            };

            let post = Post {
                content: String::from("Hello"),
                location
            };

            let js_obj = json::stringify(post);

            *response.body_mut() = Body::from(js_obj);
        }
    }

    Box::new(future::ok(response))
}