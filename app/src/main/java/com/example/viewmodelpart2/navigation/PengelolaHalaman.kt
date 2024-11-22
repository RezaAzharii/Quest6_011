package com.example.viewmodelpart2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viewmodelpart2.ui.view.screen.DetailView
import com.example.viewmodelpart2.ui.view.screen.MahasiswFormView
import com.example.viewmodelpart2.ui.view.screen.RencanaStudyView
import com.example.viewmodelpart2.ui.view.screen.SplashView
import com.example.viewmodelpart2.ui.view.viewmodel.MahasiswaViewModel
import com.example.viewmodelpart2.ui.view.viewmodel.RencanaStudyViewModel

enum class Halaman{
    Splash,
    InputMahasiswa,
    InputMataKuliah,
    TampilData
}

@Composable
fun PengelolanHalaman(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    krsViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val mahasiswaUiState = mahasiswaViewModel.mhsStateUi.collectAsState().value
    val rencanaStudiState = krsViewModel.krsStateUi.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = Modifier.padding()
    ) {
        composable(route = Halaman.Splash.name){
            SplashView (onMulaiButton ={
                navController.navigate(
                    Halaman.InputMahasiswa.name
                )
            })
        }
        composable(route = Halaman.InputMahasiswa.name){
            MahasiswFormView(
                onSubmitButtonClicked = {
                    mahasiswaViewModel.saveDataMhs(it)
                    navController.navigate(Halaman.InputMataKuliah.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
        composable(route = Halaman.InputMataKuliah.name){
            RencanaStudyView (
                mahasiswa = mahasiswaUiState,
                onSubmitButtonClicked = {
                    krsViewModel.saveDataKRS(it)
                    navController.navigate(Halaman.TampilData.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Halaman.TampilData.name){
            DetailView(
                dataDiriMhs = mahasiswaUiState,
                dataMkMhs = rencanaStudiState,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}