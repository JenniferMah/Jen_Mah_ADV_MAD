//
//  DetailViewController.swift
//  Lab Three
//
//  Created by Jennifer Mah on 3/2/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var imageViewDetail: UIImageView!
    
    var imageName: String?
    
    @IBAction func share(_ sender: Any) {
        var DogImageArray = [UIImage]()
        DogImageArray.append(imageViewDetail.image!)
        let shareScreen = UIActivityViewController(activityItems: DogImageArray, applicationActivities: nil)
        shareScreen.modalPresentationStyle = .popover
        present(shareScreen,animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let name = imageName {
            imageViewDetail.image = UIImage(named: name)
        }
    }
    
    
}
