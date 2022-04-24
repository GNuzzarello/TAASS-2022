import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SocialAuthService, GoogleLoginProvider, SocialUser } from 'angularx-social-login';
import {EcommerceService} from '../services/EcommerceService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup | undefined;
    socialUser!: SocialUser;
    isLoggedin = false;

    constructor(
        private formBuilder: FormBuilder,
        private socialAuthService: SocialAuthService,
        private ecommerceService: EcommerceService
    ) { }

    ngOnInit() {
        // init the react form object
        this.loginForm = this.formBuilder.group({
            email: ['', Validators.required],
            password: ['', Validators.required]
        });

        this.socialAuthService.authState.subscribe((user) => {
            this.socialUser = user;
            this.isLoggedin = (user != null);
            this.ecommerceService.isLoggedin = (user != null);
        });
    }

    // Initial implicite flow using OAuth2 protocol
    loginWithGoogle(): void {
        this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID);
    }

    // Logout the current session
    logOut(): void {
        this.socialAuthService.signOut();
    }
}








