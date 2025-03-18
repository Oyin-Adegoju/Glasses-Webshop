import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BestellingStatusComponent } from './bestelling-status.component';

describe('BestellingStatusComponent', () => {
  let component: BestellingStatusComponent;
  let fixture: ComponentFixture<BestellingStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BestellingStatusComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BestellingStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
