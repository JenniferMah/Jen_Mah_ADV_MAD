//
//  trivia.swift
//  Trivia App Prototype
//
//  Created by Jennifer Mah on 2/26/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation
//This will hold my stuct of trivia questions and the data I will pull from API
struct Trivia: Decodable {
    let question: String
    let answer: String
    let choices = [String]()
}

struct TriviaData: Decodable {
    var data = [Trivia]()
}
