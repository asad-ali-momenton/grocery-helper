import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class GroceryHelperService {

  constructor(private http: HttpClient) {
  }

  getAllGroceries(): Observable<any> {
    return this.http.get('//localhost:8090/grocery-helper/groceries');
  }
}
