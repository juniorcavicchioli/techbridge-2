import NavBar from "@/components/NavBar";
import Empresas from "./empresas/empresas";


export default function Home() {
  return (
    <>
      <NavBar />
      <main className=" ml-20 mr-20 p-12">
        <Empresas />
      </main>
    </>
  );
}
