import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WachtwoordVeranderenComponent } from './wachtwoord-veranderen.component';

describe('WachtwoordVeranderenComponent', () => {
  let component: WachtwoordVeranderenComponent;
  let fixture: ComponentFixture<WachtwoordVeranderenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WachtwoordVeranderenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WachtwoordVeranderenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
