import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BestellingPlaatsenComponent } from './bestelling-plaatsen.component';

describe('BestellingPlaatsenComponent', () => {
  let component: BestellingPlaatsenComponent;
  let fixture: ComponentFixture<BestellingPlaatsenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BestellingPlaatsenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BestellingPlaatsenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
