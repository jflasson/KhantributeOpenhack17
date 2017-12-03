import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'angular4-social-login';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private loggedInUser: any;
  private userScore: any;
  private users: Object[] = [];
  private domain: string;
  private userStanding = {};
  private points: number;
  private pending: number;
  private unverifiedWidth: number;
  private FOR_NEXT_LEVEL = 400;
  private width: number;
  private toplist: Object[] = [];
  

  constructor(
    private router: Router,
    private authService: AuthService,
    private http: HttpClient) {
      this.users.push(
        {firstName: 'Pelle', lastName: 'Chang', score: 1000},
        {firstName: 'Emma', lastName: 'Möller', score: 930},
        {firstName: 'Ahmed', lastName: 'Svensson', score: 900},
        {firstName: 'David', lastName: 'Stojanovski', score: 890},
        {firstName: 'Per-Göran', lastName: 'Kellner', score: 870},
        {firstName: 'Olga', lastName: 'Caballero', score: 700},
        {firstName: 'Inga', lastName: 'Wu', score: 670},
        {firstName: 'Stefan', lastName: 'Ali', score: 500},
        {firstName: 'Shaniqua', lastName: 'Smith', score: 340},
        {firstName: 'Dimitri', lastName: 'Klang', score: 300}
      );
    }

  ngOnInit() {
    this.domain = 'http://node-express-env.ft838mhenj.eu-west-1.elasticbeanstalk.com/';

    this.authService.authState.subscribe((user) => {
      if (user) {
        this.loggedInUser = user;
        this.userScore = {score: 290, ranking: 11};
      } else {
        this.loggedInUser = {firstName: 'Joel', lastName: 'Nilsson'};
        this.userScore = {score: 290, ranking: 11};
      }
    });

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

  back(){
    this.router.navigate(['/khanproofer']);
  }

}
