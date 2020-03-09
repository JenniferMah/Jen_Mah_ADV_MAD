//
//  QuestionViewController.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/5/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class QuestionViewController: UIViewController {
//UI connections
    @IBOutlet weak var score: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var question: UILabel!
    @IBOutlet weak var guessText: UITextField!
    @IBOutlet weak var QuestionsLeft: UILabel!
    @IBOutlet weak var skipsLeft: UILabel!
    
//variables
    var results = [Trivia]()
    var scoreNumber = 0
    var gameIndex = 0
    var skipIndex = 3
    var QuestionsPlayed = 0
    var category = String()

    override func viewDidAppear(_ animated: Bool) {
        nextQuestion()
        changePhoto(category:category)
        
    }
    
    func changePhoto(category: String){
        switch category{
        case "Film":
            imageView.image = UIImage(named: "film")
        case "Books":
            imageView.image = UIImage(named: "books")
        case "Music":
            imageView.image = UIImage(named: "music")
        case "Musicals & Theaters":
            imageView.image = UIImage(named: "drama")
        case "TV":
            imageView.image = UIImage(named: "TV")
        case "Video Games":
            imageView.image = UIImage(named: "videoGame")
        case "Board Games":
            imageView.image = UIImage(named: "boardGames")
        case "Mythology":
            imageView.image = UIImage(named: "myth")
        case "Animals":
            imageView.image = UIImage(named: "animals")
        case "Celebrities":
            imageView.image = UIImage(named: "celeb")
        case "Comics":
            imageView.image = UIImage(named: "comic")
        case "Anime & Manga":
            imageView.image = UIImage(named: "manga")
        default:
            imageView.image = UIImage(named: "film")
            print("Some other category")
        }
    }
    
    
    @IBAction func skip(_ sender: Any) {
        if skipIndex >= 0 {
          let alert = UIAlertController(title: "No More Skips", message: "You cannot skip anymore questions.", preferredStyle: UIAlertController.Style.alert)
          alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
          self.present(alert, animated: true, completion: nil)
        }else{
            gameIndex = gameIndex+1
            skipIndex = skipIndex - 1
            skipsLeft.text = String(skipIndex)
            nextQuestion()
        }
        
    }
    //Strip out HTML codes
    func translateQuotes(item:String) -> String{
        var newString = String()
        if item.contains("&#039;") || item.contains("&quot;"){
            let editString = item.replacingOccurrences(of: "&#039;" , with: "'")
            if editString.contains("&quot;"){
                newString = editString.replacingOccurrences(of: "&quot;" , with: "\"")
            }else{
                newString = editString
            }
        }else{
            return item
        }
        print(newString)
        return newString
    }
    func stripAmp(item:String) -> String{
        var newString = String()
        if item.contains("&amp;"){
            newString = item.replacingOccurrences(of: "&amp;" , with: "'")
        }else {
            return item
        }
        return newString
    }
    
    
    @IBAction func guess(_ sender: Any) {
        if guessText.text != nil{
            let answer: String = guessText.text!
            if answer.isEmpty == false{
                let correctAnswer = translateQuotes(item: results[gameIndex].correct_answer)
                let correct = stripAmp(item: correctAnswer)
                QuestionsPlayed = QuestionsPlayed + 1
                QuestionsLeft.text = String(10-QuestionsPlayed)
                gameIndex = gameIndex+1
                if answer == correct || answer == correct.lowercased(){
                    scoreNumber = scoreNumber+1
                    score.text = String(scoreNumber)
                }else{
                    scoreNumber = scoreNumber - 1
                    score.text = String(scoreNumber)
                }
                nextQuestion()
            }else{
                let alert = UIAlertController(title: "Please Enter Answer", message: "Please enter valid answer.", preferredStyle: UIAlertController.Style.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
                self.present(alert, animated: true, completion: nil)
            }
        }
    
    }
    
    func resetGame(){
        scoreNumber = 0
        score.text = String(scoreNumber)
        skipIndex = 3
        skipsLeft.text = String(skipIndex)
        QuestionsPlayed = 0
        QuestionsLeft.text = String(10)
        
    }
    
    func checkScore(){
        if QuestionsPlayed >= 10{ //game over
            if scoreNumber >= 6{
                let alert = UIAlertController(title: "Winner!", message: "You Won! Would you like to play agian?", preferredStyle: UIAlertController.Style.alert)
                // add the actions (buttons)
                alert.addAction(UIAlertAction(title: "Replay", style: UIAlertAction.Style.destructive, handler: { action in
                    self.resetGame()
                }))
                alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: { action in
                    self.navigationController?.popToRootViewController(animated: true)
                }))
                // show the alert
                self.present(alert, animated: true, completion: nil)
            } else{
                let alert = UIAlertController(title: "Lost", message: "You lost. Would you like to play agian?", preferredStyle: UIAlertController.Style.alert)
                // add the actions (buttons)
                alert.addAction(UIAlertAction(title: "Replay", style: UIAlertAction.Style.destructive, handler: { action in
                    self.resetGame()
                }))
                alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: { action in
                    self.navigationController?.popToRootViewController(animated: true)
                }))
                // show the alert
                self.present(alert, animated: true, completion: nil)
            }
        }else{
            if scoreNumber < 0{
                print("You lost")
                let alert = UIAlertController(title: "Lost", message: "You lost. Would you like to play agian?", preferredStyle: UIAlertController.Style.alert)
                // add the actions (buttons)
                alert.addAction(UIAlertAction(title: "Replay", style: UIAlertAction.Style.destructive, handler: { action in
                    self.resetGame()
                }))
                alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: { action in
                    self.navigationController?.popToRootViewController(animated: true)
                }))
                // show the alert
                self.present(alert, animated: true, completion: nil)
            }
        }
        
        
    }
    
    
    func nextQuestion(){
        checkScore()
        guessText.text = ""
        print(gameIndex)
        let questionText = translateQuotes(item:results[gameIndex].question)
        let questions = stripAmp(item: questionText)
        question.text = questions
        let correctAnswer = translateQuotes(item: results[gameIndex].correct_answer)
        let correct = stripAmp(item: correctAnswer)
        
        print(correct)
    
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
}
