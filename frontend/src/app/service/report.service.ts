import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UpdateSurveyResponse } from '../dto/UpdateSurveyResponse';
import { DeleteSurveyResponse } from '../dto/DeleteSurveyResponse';
import { GetReportResponse } from '../dto/GetReportResponse';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private httpClient: HttpClient) { }

  private baseURL = 'http://localhost:9091';

  updateSurvey(customerId: number, surveyResults: string[], surveyStartDate: string, surveyEndDate: string, surveyAgeFrom:string, surveyAgeTo: string ): Observable<UpdateSurveyResponse> {
    let body: string = "{\"customerId\":" + customerId +
    ", \"surveyStartDate\":\""  + surveyStartDate + "\", \"surveyEndDate\":\"" + surveyEndDate + "\"" +
    ", \"surveyAgeFrom\":\""  +  surveyAgeFrom+ "\", \"surveyAgeTo\":\"" + surveyAgeTo + "\"" +
    ", \"surveyResponses\": ["
    for(var i = 0; i < surveyResults.length; i++){
      var items = surveyResults[i].split(',');
      var answer = "{\"countryCode\":\"" + items[0] + "\",\"region\": \"" + items[1] + "\"" ;
      var questionCount = 0;
      for(var j = 2; j < items.length; j++) {
        questionCount = j-1;
        answer = answer + ", \"question" + questionCount + "\":\"" + items[j] + "\"";
      }

      answer = answer + "}";
      if ( i > 0) {
        body = body + ",";
      }
      body = body + answer;
    }

    body = body + "]}";
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

    return this.httpClient.post<UpdateSurveyResponse>(this.baseURL + '/update/survey', body, httpOptions)
      .pipe(map(response => response)
    )};

  deleteSurvey(customerId: number): Observable<DeleteSurveyResponse> {
    return this.httpClient.delete<DeleteSurveyResponse>(this.baseURL +  '/survey/' + customerId)
      .pipe(map(response => response))
    };

  getReport(customerId: number): Observable<GetReportResponse> {
    return this.httpClient.get<GetReportResponse>(this.baseURL +  '/report/' + customerId)
      .pipe(map(response => response))
  };
}
