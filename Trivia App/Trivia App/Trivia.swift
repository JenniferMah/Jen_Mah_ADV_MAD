//
//  Trivia.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/5/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation
//This will hold my stuct of trivia questions and the data I will pull from API
struct Trivia: Decodable {
    let question: String
    let correct_answer: String
    let category : String //ADDED
    // let incorrect_answers = [String]()  //Won't need this unless I change layout
}

struct TriviaData: Decodable {
    var results = [Trivia]()
}
