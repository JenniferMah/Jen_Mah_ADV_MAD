//
//  QuestionViewController.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/5/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class QuestionViewController: UIViewController,UITextFieldDelegate {
//UI connections
    @IBOutlet weak var score: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var question: UILabel!
    @IBOutlet weak var QuestionsLeft: UILabel!
    @IBOutlet weak var skipsLeft: UILabel!
    @IBOutlet weak var skipButton: UIButton!
    //Answer buttons
    @IBOutlet weak var b1: UIButton!
    @IBOutlet weak var b2: UIButton!
    @IBOutlet weak var b3: UIButton!
    @IBOutlet weak var b4: UIButton!
    
//variables
    var results = [Trivia]()
    var scoreNumber = 0
    var gameIndex = 0
    var skipIndex = 3
    var QuestionsPlayed = 5
    var category = String()
    var scoreToWin = Int()
    var answerChoicesArray = [String()]
    var correctAnswer = String()
    

    override func viewDidAppear(_ animated: Bool) {
        scoreToWin = Int.random(in: 1...5)
        nextQuestion()
        changePhoto(category:category)
    }
    
    //MARK:reset game
    func resetGame(){
        scoreNumber = 0
        skipIndex = 3
        QuestionsPlayed = 5
        gameIndex = 0
        results.shuffle()
        score.text = String(scoreNumber)
        skipsLeft.text = String(skipIndex)
        QuestionsLeft.text = String(QuestionsPlayed)
    }
    
    func checkScore(){
        if QuestionsPlayed <= 0{
            if scoreNumber >= scoreToWin {
                let alert = UIAlertController(title: "Winner!", message: "You Won! You get to decide in the argument. Would you like to play again?", preferredStyle: UIAlertController.Style.alert)
                alert.addAction(UIAlertAction(title: "Replay", style: UIAlertAction.Style.destructive, handler: { action in
                    self.resetGame()
                }))
                alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: { action in
                    self.navigationController?.popToRootViewController(animated: true)
                }))
                self.present(alert, animated: true, completion: nil)
            } else{
                let alert = UIAlertController(title: "Lost", message: "You lost. Your opponent gets to decide in the argument. Would you like to play agian?", preferredStyle: UIAlertController.Style.alert)
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
        if scoreNumber < 0{
            let alert = UIAlertController(title: "Lost", message: "You lost. Your opponent gets to decide in the argument. Would you like to play agian?", preferredStyle: UIAlertController.Style.alert)
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
    
    
    func scrambleAnswers(){
        answerChoicesArray = results[gameIndex].incorrect_answers
        answerChoicesArray.append(results[gameIndex].correct_answer)
        for i in 0...3{
            let decodedAnswers = answerChoicesArray[i].stringByDecodingHTMLEntities
            answerChoicesArray[i] = decodedAnswers
        }
        answerChoicesArray.shuffle()
    }
    
    
    func nextQuestion(){
        checkScore()
        scrambleAnswers()
        //decode HTML items
        let EncodedQuestion = results[gameIndex].question
        let DecodedQuestion = EncodedQuestion.stringByDecodingHTMLEntities
        
        question.text = DecodedQuestion
        b1.setTitle(answerChoicesArray[0], for: .normal)
        b2.setTitle(answerChoicesArray[1], for: .normal)
        b3.setTitle(answerChoicesArray[2], for: .normal)
        b4.setTitle(answerChoicesArray[3], for: .normal)
        correctAnswer = results[gameIndex].correct_answer.stringByDecodingHTMLEntities
        print(correctAnswer)
        
    
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
    
    func CheckAnswer(buttonItem: UIButton){
        if buttonItem.titleLabel?.text == correctAnswer{
            scoreNumber = scoreNumber + 1
        }else{
            scoreNumber = scoreNumber - 1
        }
        score.text = String(scoreNumber)
        QuestionsPlayed = QuestionsPlayed - 1
        QuestionsLeft.text = String(QuestionsPlayed)
        gameIndex = gameIndex + 1
        nextQuestion()
    }
    
    //MARK:Button actions
    @IBAction func b1Action(_ sender: Any) {
        CheckAnswer(buttonItem: b1)
    }
    
    @IBAction func b2action(_ sender: Any) {
        CheckAnswer(buttonItem: b2)
    }
    
    @IBAction func b3Action(_ sender: Any) {
        CheckAnswer(buttonItem: b3)
    }
    
    @IBAction func b4Action(_ sender: Any) {
        CheckAnswer(buttonItem: b4)
    }
    
    
    @IBAction func skip(_ sender: Any) {
        if skipIndex <= 0 {
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
    

    
    override func viewDidLoad() {
        super.viewDidLoad()
        question.text = ""
        skipsLeft.text = String(skipIndex)
        QuestionsLeft.text = String(QuestionsPlayed)
        
        //UI adjustments
        b1.layer.borderWidth = 2
        b1.layer.borderColor = UIColor.orange.cgColor
        b1.layer.cornerRadius = 15

        b2.layer.borderWidth = 2
        b2.layer.borderColor = UIColor.orange.cgColor
        b2.layer.cornerRadius = 15

        b3.layer.borderWidth = 2
        b3.layer.borderColor = UIColor.orange.cgColor
        b3.layer.cornerRadius = 15

        b4.layer.borderWidth = 2
        b4.layer.borderColor = UIColor.orange.cgColor
        b4.layer.cornerRadius = 15

        skipButton.layer.cornerRadius = 5.0
        // Do any additional setup after loading the view.
    }
    
}

private let characterEntities : [ Substring : Character ] = [
    // XML predefined entities:
    "&quot;"    : "\"",
    "&ldquo;"   : "\"",
    "&rdquo;"   : "\"",
    "&amp;"     : "&",
    "&apos;"    : "'",
    "&lt;"      : "<",
    "&gt;"      : ">",
    "&ntilde;" : "ñ",
    "&aacute;"  : "à",
    "&hellip;"  : ".",
    "&nbsp;"    : "\u{00a0}",
    "&diams;"   : "♦",
    "&Aring"    : "å",
]


//Extension to decode HTML items from this stackoverflow https://stackoverflow.com/questions/25607247/how-do-i-decode-html-entities-in-swift
extension String {
    var stringByDecodingHTMLEntities : String {

        // ===== Utility functions =====

        // Convert the number in the string to the corresponding
        // Unicode character, e.g.
        //    decodeNumeric("64", 10)   --> "@"
        //    decodeNumeric("20ac", 16) --> "€"
        func decodeNumeric(_ string : Substring, base : Int) -> Character? {
            guard let code = UInt32(string, radix: base),
                let uniScalar = UnicodeScalar(code) else { return nil }
            return Character(uniScalar)
        }

        // Decode the HTML character entity to the corresponding
        // Unicode character, return `nil` for invalid input.
        //     decode("&#64;")    --> "@"
        //     decode("&#x20ac;") --> "€"
        //     decode("&lt;")     --> "<"
        //     decode("&foo;")    --> nil
        func decode(_ entity : Substring) -> Character? {

            if entity.hasPrefix("&#x") || entity.hasPrefix("&#X") {
                return decodeNumeric(entity.dropFirst(3).dropLast(), base: 16)
            } else if entity.hasPrefix("&#") {
                return decodeNumeric(entity.dropFirst(2).dropLast(), base: 10)
            } else {
                return characterEntities[entity]
            }
        }

        // ===== Method starts here =====

        var result = ""
        var position = startIndex

        // Find the next '&' and copy the characters preceding it to `result`:
        while let ampRange = self[position...].range(of: "&") {
            result.append(contentsOf: self[position ..< ampRange.lowerBound])
            position = ampRange.lowerBound

            // Find the next ';' and copy everything from '&' to ';' into `entity`
            guard let semiRange = self[position...].range(of: ";") else {
                // No matching ';'.
                break
            }
            let entity = self[position ..< semiRange.upperBound]
            position = semiRange.upperBound

            if let decoded = decode(entity) {
                // Replace by decoded character:
                result.append(decoded)
            } else {
                // Invalid entity, copy verbatim:
                result.append(contentsOf: entity)
            }
        }
        // Copy remaining characters to `result`:
        result.append(contentsOf: self[position...])
        return result
    }
}
