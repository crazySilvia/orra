import {Artikel} from "../Model/Artikel";

export default function ArtikelComponent({artikel}:{artikel:Artikel}){
    return(
        <div>
            {artikel.name}
        </div>
    )


}