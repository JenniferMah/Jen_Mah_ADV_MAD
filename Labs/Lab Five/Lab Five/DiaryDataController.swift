//
//  DiaryDataController.swift
//  Lab Five
//
//  Created by Jennifer Mah on 3/16/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation
import Firebase

struct Journal {
    var date: Date
    var entryNotes: String
    var id: String
    
    func getDate() -> String {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium
        return formatter.string(from: date)
    }
}

class DiaryDataController{
       var db: Firestore!
       var diaryData = [Journal]()
    
       //closure to notify view controller of data changes
       var onDataUpdate: (([Journal]) -> Void)!
       
       init() {
           let settings = FirestoreSettings()
           Firestore.firestore().settings = settings
           db = Firestore.firestore()
       }
       
       //fetch data intially and add a listener for any new data
       func loadData() {
        let authUserID = Auth.auth().currentUser?.uid
        if let userID = authUserID {
            db.collection("users").document(userID).collection("entry").addSnapshotListener { querySnapshot, error in
               //make sure we got the collection
               guard let collection = querySnapshot else {
                   print("Error fetching collection: \(error!)")
                   return
               }
                let docs = collection.documents
               //empty our data out
               self.diaryData.removeAll()
               //append to our list
               for doc in docs {
                   //get the data dictionary from the document
                   let data = doc.data()
                   //get the data fields and downcast to appropriate types
                    print(doc)
                   let date = (data["date"] as! Timestamp).dateValue()
                   let entryNotes = data["Notes"] as! String
                   
                   //get the id
                   let id = doc.documentID
                   //construct object
                   let entry = Journal(date: date,entryNotes: entryNotes, id: id)
                   
                   self.diaryData.append(entry)
               }
               
               self.onDataUpdate(self.diaryData)
           }
        }
       }
       
    func writeData(date: Date, notes: String) {
        let authUserID = Auth.auth().currentUser?.uid
        if let userID = authUserID {
            db.collection("users").document(userID).collection("entry").addDocument(data: [
                "date": Timestamp(date: date),
                "Notes": notes,
            ], completion: { err in
                if let err = err {
                    print("Error adding document: \(err)")
                } else {
                    print("new document added successfully!")
                }
            })
        }
    }
           
}
