import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {Grocery} from './grocery';

@Injectable({
  providedIn: 'root'
})
export class GroceryHelperService {

  //URLs for CRUD operations
  allGroceriesUrl = "http://localhost:8090/grocery-helper/all-groceries";
  groceryUrl = "http://localhost:8090/grocery-helper/groceries";

  constructor(private http: HttpClient) {
  }

  //Fetch all groceries
  getAllGroceries(): Observable<any> {
    console.log('calling: getAllGroceries ' + this.allGroceriesUrl);
    return this.http.get(this.allGroceriesUrl);
  }

  //Create grocery
  createGrocery(grocery: Grocery): Observable<any> {
    console.log('calling: createGrocery ' + this.groceryUrl + '/' + grocery.id);
    return this.http.post(this.groceryUrl, grocery);
  }

  //Fetch grocery by id
  getGroceryById(groceryId: string): Observable<any> {
    console.log('calling: getGroceryById ' + this.groceryUrl + '/' + groceryId);
    return this.http.get(this.groceryUrl + '/' + groceryId);
  }

  //Fetch grocery by category
  getGroceryByCategory(category: string): Observable<any> {
    console.log('calling: getGroceryByCategory ' + this.groceryUrl + '?category=' + category);
    return this.http.get(this.groceryUrl + '?category=' + category);
  }

  //Update grocery
  updateGrocery(grocery: Grocery): Observable<any> {
    console.log('calling: updateGrocery ' + this.groceryUrl + '/' + grocery.id);
    return this.http.put(this.groceryUrl, grocery);
  }

  //Delete grocery
  deleteGroceryById(groceryId: string): Observable<any> {
    console.log('calling: deleteGroceryById ' + this.groceryUrl + '/' + groceryId);
    return this.http.delete(this.groceryUrl + '/' + groceryId);
  }

}
