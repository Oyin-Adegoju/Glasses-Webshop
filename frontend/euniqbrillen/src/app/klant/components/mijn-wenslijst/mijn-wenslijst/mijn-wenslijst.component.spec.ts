import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MijnWenslijstComponent } from './mijn-wenslijst.component';

describe('MijnWenslijstComponent', () => {
  let component: MijnWenslijstComponent;
  let fixture: ComponentFixture<MijnWenslijstComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MijnWenslijstComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MijnWenslijstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
