import pygame
import mainmenu
import const
def main():
    """ The main function of the system. Starts the system with a full screen
        window of resolution 800x600 """
    const.screen = pygame.display.set_mode((800,600),pygame.FULLSCREEN)
    pygame.display.set_caption(":: Sim Traffic ::")
    main = mainmenu.MainMenu(const.screen)
    main.start()

main()
