module Handlers

open Giraffe
open Microsoft.AspNetCore.Http
open System.IO
open System.Text
open FSharp.Control.Tasks.V2.ContextInsensitive

open Models
open System

let rnd = new Random();

let randomPost =
    let lat = float(rnd.Next()) + rnd.NextDouble()
    let long = float(rnd.Next()) + rnd.NextDouble()
    { text = "Holi"; location = { lat = lat; long = long } }

let handlePosts =
    fun (next : HttpFunc) (ctx : HttpContext) -> 
        task {
            ctx.SetStatusCode 200
            let! model = ctx.BindModelAsync<Post>()
            let post = { text = ""; location = { lat = 123.124; long = 123.23 }}
            return! json model next ctx
        }

let getPosts =
    fun (next : HttpFunc) (ctx : HttpContext) ->
        task {
            let! model = ctx.BindModelAsync<PostRequest>()
            let posts = [for _ in 1 .. model.continuationToken -> randomPost ]
            let response = { posts = posts; continuationToken = model.continuationToken + posts.Length }
            return! json response next ctx
        }

let createPost =
    fun (next : HttpFunc) (ctx : HttpContext) ->
        task {
            ctx.SetStatusCode 201
            let! model = ctx.BindModelAsync<Post>()
            return! json model next ctx
        }