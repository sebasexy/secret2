module Models

[<CLIMutable>]
type Point = { lat:double; long:double }

[<CLIMutable>]
type Post = { text:string; location:Point }

[<CLIMutable>]
type PostRequest = { location:Point; range:int; continuationToken:int }

[<CLIMutable>]
type PostResponse = { posts:Post list; continuationToken:int }