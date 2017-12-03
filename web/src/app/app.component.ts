import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Khan Proof Quest';
  private isAuthenticated: boolean;

  constructor(
    private router: Router,
    private route: ActivatedRoute) {
}

ngOnInit() {
  console.log('On init: ' + this.router.url);
}

changeAuthentication(event: any) {
  this.isAuthenticated = event;
  console.log('Event: ' + event);
}


}
