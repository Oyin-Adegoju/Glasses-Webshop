import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AanmeldenComponent } from './aanmelden.component';

describe('AanmeldenComponent', () => {
  let component: AanmeldenComponent;
  let fixture: ComponentFixture<AanmeldenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AanmeldenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AanmeldenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
