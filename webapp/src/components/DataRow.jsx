export default function DataRow({ empresa }) {
    //img nao funcionou
    const { nome, ramo, imagemUrl } = empresa;
    console.log(imagemUrl);
    return (
            <div id="data-row" className="bg-neutral-950 rounded-3xl group/row flex flex-col justify-center items-center hover:bg-neutral-800 p-2 cursor-pointer m-2">

                <div className="flex h-32 w-32 rounded-full overflow-hidden">
                    <img src={imagemUrl} alt="avatar do usuário" />
                </div>
                <div className="flex gap-1">
                    <span>{nome}</span>
                </div>
                <div className="flex items-center">
                    <span>{ramo}</span>
                </div>
            </div>
    );
}