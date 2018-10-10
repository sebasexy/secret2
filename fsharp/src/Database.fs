module Database

open Models
open MongoDB
open MongoDB.Bson
open MongoDB.Driver.Linq
open MongoDB.Driver
open FSharp.Control.Tasks.V2.ContextInsensitive
open System
open System.Linq

let create (collection:IMongoCollection<Post>, model:Post) =
    task{
        return! collection.InsertOneAsync(model)
    }

let private dist location location2 =
    let toRadians x =
        (x * Math.PI) / 180.0
    
    let R = 6371.0 * 10.0 ** 3.0
    let phi1 = location.lat |> toRadians
    let phi2 = location2.lat |> toRadians

    let deltaPhi1 = (location.lat - location2.lat) |> toRadians
    let deltaPhi2 = (location.long - location2.long) |> toRadians

    let aPt1 = Math.Sin(deltaPhi1/2.0) * Math.Sin(deltaPhi1/2.0)
    let aPt2 = Math.Cos(phi1) * Math.Cos(phi2)
    let aPt3 = Math.Sin(deltaPhi2/2.0) * Math.Sin(deltaPhi2/2.0)

    let a = aPt1 + aPt2 * aPt3

    let c = 2.0 * Math.Atan2(Math.Sqrt(a), Math.Sqrt(1.0-a))

    R * c

let search (collection:IMongoCollection<Post>, location:Point, range:float) = 
    task {
        let! results = collection.FindAsync(fun x -> 
            let distance = dist x.location location
            distance <= range
        )
        
        return! results.ToListAsync()
    }