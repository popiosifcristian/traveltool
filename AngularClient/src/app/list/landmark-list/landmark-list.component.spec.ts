import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LandmarkListComponent } from './landmark-list.component';

describe('LandmarkListComponent', () => {
  let component: LandmarkListComponent;
  let fixture: ComponentFixture<LandmarkListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LandmarkListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LandmarkListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
