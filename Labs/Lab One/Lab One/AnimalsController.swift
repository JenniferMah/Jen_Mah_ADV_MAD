//
//  AnimalsController.swift
//  Lab One
//
//  Created by Jennifer Mah on 2/2/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation

enum DataError: Error{
    case BadFilePath
    case CouldNotDecodeData
    case NoData
    
}

class AnimalController{
    let fileName = "animals"
    var animalData: [AnimalsList]?
    
    //Methods
    func LoadData()throws{
        if let PathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            let decoder = PropertyListDecoder()
            do{
                let data = try Data(contentsOf: PathURL)
                animalData = try decoder.decode([AnimalsList].self, from: data) //we want to decode to an array of animal breeds
            }catch{
                throw DataError.CouldNotDecodeData
            }
        }else{
            throw DataError.BadFilePath
        }
    }
    
    func GetAnimalTypes() throws -> [String]{
        var animalTypes = [String]()
        
        if let data = animalData{
            for types in data{
                animalTypes.append(types.Animal)
            }
            return animalTypes
        }else{
            throw DataError.NoData
        }
    }
    
    func getBreeds(idx:Int) throws -> [String]{
        if let data = animalData{
            return data[idx].Breed
        }else{
            throw DataError.NoData
        }
    }
    
    
}
