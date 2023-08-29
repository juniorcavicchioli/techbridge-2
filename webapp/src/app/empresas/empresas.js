import DataRow from "@/components/DataRow";

async function getEmpresas() {
    const url = "http://localhost:8080/techbridge/api/empresa";
    const response = await fetch(url);
    if (!response.ok) throw new Error("Dados não carregados");
    const data = await response.json();
    return data._embedded.entityModelList;
}

export default async function Empresas() {
    const data = await getEmpresas();

    return (
        <>
            <h2 className="text-2xl font-bold text-center">Empresas</h2>

            <div id="data" className="text-slate-300 m-1 ">
                {data.map(empresa => <DataRow key={empresa.id} empresa={empresa} />)}
            </div>
        </>
    );
}