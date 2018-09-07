use json::JsonValue;
use rand::random;

pub struct Point {
    pub lat:f32,
    pub long:f32
}

pub struct Post {
    pub content:String,
    pub location:Point
}

pub struct PostRequest {
    pub location:Point,
    pub continuation_token:i32
}

pub struct PostResponse {
    pub posts:Vec<Post>,
    pub continuation_token:i32
}

impl Point {
    pub fn new() -> Point {
        Point { lat: 0.0f32, long: 0.0f32 }
    }

    pub fn random() -> Point{
        let lat = random();
        let long = random();
        Point { lat, long }
    }
}

impl Into<JsonValue> for PostResponse {
    fn into(self) -> JsonValue {
        object! {
            "posts" => self.posts,
            "continuation_token" => self.continuation_token
        }
    }
}

impl Into<JsonValue> for Point {
    fn into(self) -> JsonValue {
        object! {
            "lat" => self.lat,
            "long" => self.long
        }
    }
}

impl Into<JsonValue> for PostRequest {
    fn into(self) -> JsonValue {
        object! {
            "location" => self.location,
            "continuationToken" => self.continuation_token
        }
    }
}

impl Into<JsonValue> for Post {
    fn into(self) -> JsonValue {
        object! {
            "content" => self.content,
            "location" => self.location
        }
    }
}