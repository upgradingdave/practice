module Paths_practice (
    version,
    getBinDir, getLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
catchIO = Exception.catch


version :: Version
version = Version {versionBranch = [0,1,0,0], versionTags = []}
bindir, libdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/Users/dparoulek/code/practice/hs/.cabal-sandbox/bin"
libdir     = "/Users/dparoulek/code/practice/hs/.cabal-sandbox/lib/x86_64-osx-ghc-7.10.3/practice-0.1.0.0"
datadir    = "/Users/dparoulek/code/practice/hs/.cabal-sandbox/share/x86_64-osx-ghc-7.10.3/practice-0.1.0.0"
libexecdir = "/Users/dparoulek/code/practice/hs/.cabal-sandbox/libexec"
sysconfdir = "/Users/dparoulek/code/practice/hs/.cabal-sandbox/etc"

getBinDir, getLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "practice_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "practice_libdir") (\_ -> return libdir)
getDataDir = catchIO (getEnv "practice_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "practice_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "practice_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
