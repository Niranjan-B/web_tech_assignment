import { TestBed, inject } from '@angular/core/testing';

import { FetchDataService } from './fetch-data.service';

describe('FetchDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FetchDataService]
    });
  });

  it('should ...', inject([FetchDataService], (service: FetchDataService) => {
    expect(service).toBeTruthy();
  }));
});
