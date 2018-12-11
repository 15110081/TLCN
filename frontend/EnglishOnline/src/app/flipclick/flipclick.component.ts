import { Component, OnInit } from '@angular/core';
import { Word } from '../model/word';
import { FlashcardService } from '../service/flashcard.service';
// import * as $ from 'jquery';
declare var $: any;
@Component({
  selector: 'app-flipclick',
  templateUrl: './flipclick.component.html',
  styleUrls: ['./flipclick.component.scss']
})
export class FlipclickComponent implements OnInit {
  data: any;
  logs: string[] = [];
  image="http://localhost:8081/wordapi/image/";
  selectedWord={id:null, vocabulary: '', phonetic: '', note:'' , definition:'' , typeword:'' , title:''};
  constructor(private flashCardService:FlashcardService) {
    this.loadWord();
    // this.selectedWord=this.data[20];
   }

  ngOnInit() {
    $(document).ready(function(){
    $(".card").click(function(){
      $(this).toggleClass("flipped");
    });
  });
  }
  checkundefined():any{
    if(this.selectedWord===undefined)return false;
    return true;
  }
  onSelectedWord(id:number){
    console.log(id);
   this.image="http://localhost:8081/wordapi/image/logo/";
    this.flashCardService.getWordFromId(id).subscribe(res=>{
      if(res.code==1){
        this.selectedWord=res.data;
        console.log(`${JSON.stringify(res.data)}`);
        this.image=this.image+res.data.id;
      }
    });
  //  this.selectedWord=this.data[id];

  }
  items = Array.from({length: 90}).map((_, i) => `Item #${i}`);
  loadWord() {
    this.flashCardService.getAllWord().subscribe(res => {
      if (res.code == 1) {
        this.data = res.data;
        if (this.data.length > 1) {
          this.selectedWord = this.data[0];
          this.image=this.image+this.data[0].id;
        }
        this.logs.unshift("[" + new Date().toLocaleString() + "] " + res.message);
      }
    })
  }
  logging(message) {
    this.loadWord();
    this.logs.unshift("[" + new Date().toLocaleString() + "] " + message);
  }
}
