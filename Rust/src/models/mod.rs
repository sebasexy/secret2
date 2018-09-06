use json::JsonValue;

pub struct Point {
    pub lat:f32,
    pub lon:f32
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
            "lon" => self.lon
        }
    }
}

impl Into<JsonValue> for PostRequest {
    fn into(self) -> JsonValue {
        object! {
            "location" => self.location,
            "continuation_token" => self.continuation_token
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