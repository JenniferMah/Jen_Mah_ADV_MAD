//
//  ThirdViewController.swift
//  Lab One
//
//  Created by Jennifer Mah on 2/3/20.
//  Copyright © 2020 Jennifer Mah. All rights reserved.
//

import UIKit
import AVFoundation

class ThirdViewController: UIViewController,AVAudioPlayerDelegate, AVAudioRecorderDelegate {
//UI elements
    @IBOutlet weak var RecordButton: UIButton!
    @IBOutlet weak var PlayButton: UIButton!
    @IBOutlet weak var StopButton: UIButton!
    
//var
    let audioSession = AVAudioSession.sharedInstance()
    var audioPlayer: AVAudioPlayer?
    var audioRecorder:AVAudioRecorder?
      
    let filename = "audio.m4a"
          
    
    
   
    override func viewDidLoad() {
        super.viewDidLoad()
        //path information to save file on phone
       let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
       let docDir = dirPath[0]
       let audioFilePath = docDir.appendingPathComponent(filename)
       
       //start to configure audio session
       do{
           try audioSession.setCategory(AVAudioSession.Category.playAndRecord, mode: .default, options: .init(rawValue:1))//adjust volume at end
       }catch{
           print(error)
       }
       
       //Create dictionary of settings for our audio reccorder
       let settings = [
           AVFormatIDKey: Int(kAudioFormatMPEG4AAC), //audio codec
           AVSampleRateKey: 1200, //sample rate
           AVNumberOfChannelsKey: 1,
           AVEncoderAudioQualityKey:AVAudioQuality.high.rawValue //bit rate
       ]
       
       do{
           audioRecorder = try AVAudioRecorder(url: audioFilePath, settings: settings)
           audioRecorder?.prepareToRecord()
           print("Ready to record")
           
       }catch{
           print(error)
       }

    }
    //Button Methods
    @IBAction func recordAudio(_ sender: Any) {
        if let recorder = audioRecorder {
            if recorder.isRecording == false {
                PlayButton.isEnabled = false
                StopButton.isEnabled = true
                recorder.delegate = self
                recorder.record()
            }
        } else {
            print("No audio recorder instance")
        }
    }
    @IBAction func playSound(_ sender: Any) {
        if audioRecorder?.isRecording == false{
            StopButton.isEnabled = true
            RecordButton.isEnabled = false

            do{
                try audioPlayer = AVAudioPlayer(contentsOf: (audioRecorder?.url)!)
                try audioSession.setCategory(AVAudioSession.Category.playback)
                audioPlayer!.delegate = self
                audioPlayer!.prepareToPlay()
                audioPlayer!.play()
            }catch {
                print("can't play audio")
            }
        }
    }
    
    @IBAction func stopAudio(_ sender: Any) {
        StopButton.isEnabled = false
        PlayButton.isEnabled = true
        RecordButton.isEnabled = true
       if audioRecorder?.isRecording == true {
           audioRecorder?.stop()
       }else{
           audioPlayer?.stop()
           do{
               try audioSession.setCategory(AVAudioSession.Category.playAndRecord)
           }catch{
               print(error)
           }
       }
    }
    
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        RecordButton.isEnabled = true
        StopButton.isEnabled = false
        do {
            try audioSession.setCategory(AVAudioSession.Category.playAndRecord)
        } catch {
            print(error.localizedDescription)
        }
    }

}
