import { TestBed } from '@angular/core/testing';

import { Report.ServiceService } from './report.service.service';

describe('Report.ServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: Report.ServiceService = TestBed.get(Report.ServiceService);
    expect(service).toBeTruthy();
  });
});
