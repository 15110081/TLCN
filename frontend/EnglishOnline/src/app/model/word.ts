export class Word {
  id: number;
  vocabulary: string;
  phonetic: string;
  note: string;
  definition: string;
  typeword: string;
  title: string;
 
  constructor(
    id: number,
    definition: string,
    note: string,
    phonetic: string,
    title: string,
    typeword: string,
    vocabulary: string
  ) {
      this.id=id;
      this.definition=definition;
      this.note=note;
      this.phonetic=phonetic;
      this.title=title;
      this.typeword=typeword;
      this.vocabulary=vocabulary;
  }
}
