//
//  Drinks.swift
//  Lab Four
//
//  Created by Jennifer Mah on 3/3/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation

struct Drinks: Decodable{
    let strDrink: String
    let strDrinkThumb: String
    let strInstructions: String
}

struct DrinksData:Decodable {
    var drinks = [Drinks]()
}
