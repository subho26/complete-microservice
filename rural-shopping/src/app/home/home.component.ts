import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { first, takeUntil } from 'rxjs/operators';
import { AuthenticationService } from '../shared/services/authentication.service';
import { ProductService } from '../shared/services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentUser: any;
  destroy$ = new Subject;
  products: any;

  constructor(private authenticationService: AuthenticationService, private productService: ProductService,
    private router: Router) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {
  }

  public callProduct(): void {
    // this.productService.getAllProducts().pipe(takeUntil(this.destroy$)).subscribe({
    //   next: data => {
    //     this.products = data;
    //   },
    //   error: error => {
    //     console.error('There was an error!', error.message);
    //   }
    // });
    this.authenticationService.login('user1', 'pwd1')
          .pipe(first())
          .subscribe(
              data => {
                  console.log('-----------------');
              },
              error => {
                  console.log('================');
              });
  }
}
