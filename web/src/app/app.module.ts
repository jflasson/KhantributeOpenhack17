import { NotFoundComponent } from './not-found/not-found.component';
import { LoginComponent } from './login/login.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { ProofReaderComponent } from './proof-reader/proof-reader.component';
import { HeaderComponent } from './header/header.component';
 import { SocialLoginModule, AuthServiceConfig } from 'angular4-social-login';
 import { GoogleLoginProvider } from 'angular4-social-login';
import { HttpClientModule } from '@angular/common/http';

const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('661055545793-1td82v29r6pskidkmuji7jvehrkdd7op.apps.googleusercontent.com')
  }
  /*{
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider('Facebook-App-Id')
  }*/
]);

const appRoutes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'khanproofer', component: ProofReaderComponent},
  {path: 'profile', component: ProfileComponent},
//  {path: 'dashboard', component: DashboardComponent},
  {path: '**', component: NotFoundComponent},
];

export function provideConfig() {
  return config;
}


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    NotFoundComponent,
    ProofReaderComponent,
    HeaderComponent
],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
     SocialLoginModule
  ],
  providers: [ {
    provide: AuthServiceConfig,
    useFactory: provideConfig
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
