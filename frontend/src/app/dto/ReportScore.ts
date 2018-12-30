
export class ReportScore {

  veryWell: number;
  well: number;
  moderate: number;
  badly: number;
  veryBadly: number;
  validAnswers: number;
  totalAnswers: number;
  mean: string;
  medianstring: string;
  standardDeviation: string;
  yourMean: string;
  yourMedian: string;
  ebbiScore: string;
  ebbiIndex: string;

  constructor(veryWell, well, moderate, badly, veryBadly, mean, medianstring, standardDeviation, yourMean, yourMedian, ebbiScore, ebbiIndex) {
    this.veryWell = veryWell;
    this.well = well;
    this.moderate = moderate;
    this.badly = badly;
    this.veryBadly = veryBadly;
    this.mean = medianstring;
    this.standardDeviation = standardDeviation;
    this.yourMean = yourMean;
    this.yourMedian = yourMedian;
    this.ebbiScore = ebbiScore;
    this.ebbiIndex = ebbiIndex;
  }

}
