import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-proof-reader',
  templateUrl: './proof-reader.component.html',
  styleUrls: ['./proof-reader.component.css']
})
export class ProofReaderComponent implements OnInit {
  private width: number;
  private unverifiedWidth: number;
  private points: number;
  private pending: number;
  private FOR_NEXT_LEVEL = 400;
  private textStrings: Object[] = [];
  private stringToShow: number;
  private domain: string;
  private userStanding = {};

  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.stringToShow = 0;
    this.domain = 'http://node-express-env.ft838mhenj.eu-west-1.elasticbeanstalk.com/';

    // Add example strings
    this.textStrings.push(
      {english: 'The dog that stole Christmas', swedish: 'Hunden som stal hjulet'},
      {english: 'There are too many guys on Tinder', swedish: 'Det finns för många killar på Tinder'},
      {english: 'Quadratic equations can be easily solved with this formula: ', swedish: 'Kvadratiska ekvationer kan lätt lösas med följande formel: '},
      {english: 'C++ includes every programming feature known to man', swedish: 'Hillay borde ha vunnit valet'},
      {english: 'Final sentence before new level', swedish: 'Sista meningen innan nästa nivå'},
    );
      // Api call
      this.http.get(this.domain + 'texts/score').subscribe(data => {
        // Read the result field from the JSON response.
        console.log('Data: ');
        console.log(data);
        this.userStanding = data;
        this.points = data['score'];
        this.pending = data['pending'];

        this.width = (this.points / this.FOR_NEXT_LEVEL) * 100;
        this.unverifiedWidth = (this.pending / this.FOR_NEXT_LEVEL) * 100;
      });
  }

  hasVoted(direction: string): void {
    this.pending += 5;
    this.unverifiedWidth = (this.pending / this.FOR_NEXT_LEVEL) * 100;
    this.changeItem();

    if (direction === 'up') {
      console.log(direction);
    }
    else if (direction === 'down') {
      console.log(direction);
    }
  }

  notSure(): void {
    this.changeItem();
  }

  changeItem(): void {
    this.stringToShow += 1;
  }

 /* sendResultsToServer() {
    this.http.post(this.domain + '')
  }*/

}
