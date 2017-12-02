import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'angular4-social-login';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private loggedInUser: any;
  private users: Object[] = [];

  constructor(
    private router: Router,
    private authService: AuthService) {
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
        {firstName: 'Dimiti', lastName: 'Klang', score: 300}
      );
    }

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      if (user) {
        this.loggedInUser = user;
      } else {
        this.loggedInUser = {firstName: 'Joel', lastName: 'Nilsson', score: 290, ranking: 11};
      }
    });
  }

  back(){
    this.router.navigate(['/khanproofer']);
  }

}
