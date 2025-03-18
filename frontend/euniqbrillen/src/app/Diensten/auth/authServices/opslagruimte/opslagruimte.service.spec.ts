import { TestBed } from '@angular/core/testing';

import { OpslagruimteService } from './opslagruimte.service';

describe('OpslagruimteService', () => {
  let service: OpslagruimteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OpslagruimteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
