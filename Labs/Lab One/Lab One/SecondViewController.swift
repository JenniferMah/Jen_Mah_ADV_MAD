//
//  SecondViewController.swift
//  Lab One
//
//  Created by Jennifer Mah on 2/2/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {
//Scheme for each app you need to open is unique and dont forget to add to manaifest

    let mapsScheme = "mapitem://"
    let googleMapsScheme = "comgooglemaps://"
    let searchScheme = "https://www.animalhumanesociety.org/adoption"


    
    func checkAvalible(scheme:String) -> Bool{
       if let url = URL(string: scheme){
           return UIApplication.shared.canOpenURL(url)
       }
       return false
    }
       
       
   func openExternalApps(scheme:String){
       if let Url = URL(string: scheme){
           UIApplication.shared.open(Url, options: [:], completionHandler: {
               (success) in
               print("opened \(scheme)")
           })
       }
   }
       
    @IBAction func loadExternalSite(_ sender: Any) {
        let searchInstalled = checkAvalible(scheme: searchScheme)
        let googleMapsInstalled=checkAvalible(scheme: googleMapsScheme)
        if searchInstalled{
            openExternalApps(scheme: searchScheme)
        }else if googleMapsInstalled{
           openExternalApps(scheme: googleMapsScheme)
        }else{
           openExternalApps(scheme: mapsScheme)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

