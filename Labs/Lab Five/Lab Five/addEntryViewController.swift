//
//  addEntryViewController.swift
//  Lab Five
//
//  Created by Jennifer Mah on 3/16/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class addEntryViewController: UIViewController, UITextViewDelegate {

    //UI ITEMS
    @IBOutlet weak var DatePicker: UIDatePicker!
    @IBOutlet weak var notes: UITextView!
    
    
    //Variables Ect
    var entryNotes: String?
    var date: Date?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        let tapRecognizer = UITapGestureRecognizer()
        tapRecognizer.addTarget(self, action: #selector(addEntryViewController.didTapView))
        self.view.addGestureRecognizer(tapRecognizer)
        
    }
    @objc func didTapView(){
      self.view.endEditing(true)
    }
    
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        if(text == "\n") {
            textView.resignFirstResponder()
            return false
        }
        return true
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
          //check to make sure we're only saving when the user presses save button
      if segue.identifier == "Save" {
          date = DatePicker.date
          if notes.text.isEmpty == false {
              entryNotes = notes.text
          }
      }
    }

}
