import { AuthService } from 'angular4-social-login';
import { Router, ActivatedRoute, NavigationEnd} from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private isAuthenticated: boolean;
  private user: any;
  private isProfilePage: boolean;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
  private authService: AuthService) { }

  ngOnInit() {
    this.router.events.subscribe( (event) => {
      if (event instanceof NavigationEnd) {
        if (event.url === '/login') {
          this.isAuthenticated = false;
        }
        else {
          this.isAuthenticated = true;
        }
        console.log('PROFILE? ', this.isProfilePage);
        event.url === '/profile' ? this.isProfilePage = true : this.isProfilePage = false;
        console.log('PROFILE? ', this.isProfilePage);
        
        console.log('Navigation end: ' + event.url);
      }
    });

    this.authService.authState.subscribe((user) => {
      if (user) {
        console.log("Found user");
        this.user = user;
        this.isAuthenticated = (user != null);
      }

    });
  }


  seeProfile(){
    this.router.navigate(['/profile']);
  }
  
  signOut() {
    this.isAuthenticated = false;
    this.authService.signOut().then( () => {
      this.router.navigate( ['/login'] );
    });


  }

}
