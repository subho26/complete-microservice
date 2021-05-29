import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { ProductService } from '../shared/services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentUser: any;

  constructor(private authenticationService: AuthenticationService, private productService: ProductService) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {
  }

  public callProduct(): void{
    let ss = this.productService.getAllProducts();
    console.log(ss);
  }
}
