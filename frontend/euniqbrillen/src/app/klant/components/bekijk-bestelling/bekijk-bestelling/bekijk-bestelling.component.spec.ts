import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BekijkBestellingComponent } from './bekijk-bestelling.component';

describe('BekijkBestellingComponent', () => {
  let component: BekijkBestellingComponent;
  let fixture: ComponentFixture<BekijkBestellingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BekijkBestellingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BekijkBestellingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
