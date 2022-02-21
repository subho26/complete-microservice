import { Routes, RouterModule } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductListComponent } from './product/product-list.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './shared/_helper/auth.guard';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'products/:id', component: ProductDetailComponent },
  { path: 'cart', component: CartComponent },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);