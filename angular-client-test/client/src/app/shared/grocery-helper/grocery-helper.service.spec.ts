import { TestBed } from '@angular/core/testing';

import { GroceryHelperService } from './grocery-helper.service';

describe('GroceryHelperService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GroceryHelperService = TestBed.get(GroceryHelperService);
    expect(service).toBeTruthy();
  });
});
