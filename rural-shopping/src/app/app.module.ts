import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { appRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SharedModule } from './shared/shared.module';
import { AlertComponent } from './alert/alert.component';
import { ProductModule } from './product/product.module';

@NgModule({
    imports: [BrowserModule, appRoutingModule, SharedModule, ReactiveFormsModule, FormsModule, ProductModule],
    declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent, AlertComponent],
    bootstrap: [AppComponent]
})
export class AppModule { }