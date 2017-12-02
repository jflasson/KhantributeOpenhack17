import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'angular4-social-login';
import { GoogleLoginProvider } from 'angular4-social-login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private users: Object[] = [];
  @Output() changeAuthentication: EventEmitter<boolean> = new EventEmitter();

  constructor(
    private router: Router,
    private authService: AuthService) {

   }

  ngOnInit() {
    const user1 = {username: 'admin', password: 'password'};
    const user2 = {username: 'root', password: ''};
    this.users.push(user1, user2);
    this.changeAuthentication.emit(false);
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  signOut(): void {
    this.authService.signOut();
  }

  signIn(username: string, password: string) {
    this.changeAuthentication.emit(true);
    for (const item of this.users) {
      console.log(item);
      if (item['username'] === username && item['password'] === password ) {
        this.changeAuthentication.emit(true);
        this.router.navigate( ['khanproofer']);
      }
    }
  }



}
