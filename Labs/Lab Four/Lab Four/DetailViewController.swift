//
//  DetailViewController.swift
//  Lab Four
//
//  Created by Jennifer Mah on 3/4/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var instructions: UILabel!
    
    var drinkName = String()
    var drinkInstructions = String()
    var drinkPic = String()
    //set image from URL
    func setImage(url: String) {
        guard let imageURL = URL(string: url) else { return }
        DispatchQueue.global().async {
            guard let imageData = try? Data(contentsOf: imageURL) else { return }
            let image = UIImage(data: imageData)
            DispatchQueue.main.async {
                self.imageView.image = image
            }
        }
    }

    override func viewDidAppear(_ animated: Bool) {
        name.text = drinkName
        instructions.text = drinkInstructions
        setImage(url: drinkPic)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
