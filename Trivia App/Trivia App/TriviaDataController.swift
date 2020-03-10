//
//  TriviaDataController.swift
//  Trivia App
//
//  Created by Jennifer Mah on 3/5/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import Foundation

class TriviaDataController{
    var currentTrivia = TriviaData()
    var onDataUpdate: ((_ data: [Trivia]) -> Void)?

    
    func loadJson(category: String) {
        let urlPath = "https://opentdb.com/api.php?amount=20&category=" + category + "&type=multiple"
        
        guard let url = URL(string: urlPath) else {
            print("bad url")
            return
        }

        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            guard statusCode == 200 else {
                print("file download problem. status code: \(statusCode)")
                return
            }
            DispatchQueue.main.async {self.parseJson(rawData: data!)}
        })
        session.resume()
    }
    func parseJson(rawData:Data){
        do {
               //try to decode the response
               let parsedData = try JSONDecoder().decode(TriviaData.self, from: rawData)
               currentTrivia.results.removeAll()
               //add all the campsite entries to our class property that stores the current campsites
               for triviaItems in parsedData.results {
                   currentTrivia.results.append(triviaItems)
                    //print(triviaItems)
               }
            } catch {
               //something went wrong parsing the data — throw error!
               print("json error")
               print(error)
            }
            onDataUpdate?(currentTrivia.results)
        }

}
