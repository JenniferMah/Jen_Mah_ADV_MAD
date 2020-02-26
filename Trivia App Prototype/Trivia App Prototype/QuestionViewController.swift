//
//  QuestionViewController.swift
//  Trivia App Prototype
//
//  Created by Jennifer Mah on 2/26/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class QuestionViewController: UIViewController {
    //UI control references
    
    @IBOutlet weak var imageReference: UIImageView! //Will want to change image based on category
    @IBOutlet weak var questionReference: UILabel! //add question
    @IBOutlet weak var guessTextField: UITextField! //get the guess from this text field
    
    
    @IBAction func skipButton(_ sender: Any) {
        //skip to next item in the questionList
    }
    
    @IBAction func guessButton(_ sender: Any) {
        //check if answer is correct
    }
    
    //This will be the main game interface
    var selectedCategory = 0
    var triviaDataController = TriviaDataController()
    var questionsList = [String]()
    var answers = [String]() //MAYBE WILL USE DICTIONARY TO STORE information need to look at API more
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    func score(){
        //keeps track of scores
    }
    
    func gameControl(){
        //This is where the game control will take place
    }
    
    

 
}
